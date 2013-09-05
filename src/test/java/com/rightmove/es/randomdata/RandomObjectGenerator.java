package com.rightmove.es.randomdata;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;

@SuppressWarnings({"rawtypes", "unchecked"})
public class RandomObjectGenerator {

	private static final int						DEFAULT_MIN_STRING_LENGTH	= 1;
	private static final int						DEFAULT_MAX_STRING_LENGTH	= 10;
	private static final int						MAX_LIST_LENGTH				= 10;
	private static final int						MIN_LIST_LENGTH				= 1;
	private static final String						SETTER_PREFIX				= "set";

	private final long								seed;
	private final RandomDataGenerator				randomizer;
	private final Map<String, Class<?>>				keyTypeMap					= new HashMap<String, Class<?>>();
	private final Map<String, Class<?>>				componentTypeMap			= new HashMap<String, Class<?>>();
	private final Map<String, FloatRange>			floatRanges					= new HashMap<String, RandomObjectGenerator.FloatRange>();
	private final Map<String, DoubleRange>			doubleRanges				= new HashMap<String, RandomObjectGenerator.DoubleRange>();
	private final Map<String, ShortRange>			shortRanges					= new HashMap<String, RandomObjectGenerator.ShortRange>();
	private final Map<String, IntRange>				intRanges					= new HashMap<String, RandomObjectGenerator.IntRange>();
	private final Map<String, LongRange>			longRanges					= new HashMap<String, RandomObjectGenerator.LongRange>();
	private final Map<String, StringLengthRange>	stringLengthRanges			= new HashMap<String, RandomObjectGenerator.StringLengthRange>();
	private final Stack<String>						setterPath					= new Stack<String>();
	private final Collection<String>				ignoredSetters				= new HashSet<String>();
	private final Collection<Class<?>>				ignoredTypes				= new HashSet<Class<?>>();
	private boolean									trimStrings					= false;

	private static class FloatRange {
		private final float	min;
		private final float	max;

		public FloatRange(final float min, final float max) {
			this.min = min;
			this.max = max;
		}
	}

	private static class DoubleRange {
		private final double	min;
		private final double	max;

		public DoubleRange(final double min, final double max) {
			this.min = min;
			this.max = max;
		}
	}

	private static class ShortRange {
		private final short	min;
		private final short	max;

		public ShortRange(final short min, final short max) {
			this.min = min;
			this.max = max;
		}
	}

	private static class IntRange {
		private final int	min;
		private final int	max;

		public IntRange(final int min, final int max) {
			this.min = min;
			this.max = max;
		}
	}

	private static class LongRange {
		private final long	min;
		private final long	max;

		public LongRange(final long min, final long max) {
			this.min = min;
			this.max = max;
		}
	}

	private static class StringLengthRange {
		private final int	min;
		private final int	max;

		public StringLengthRange(final int min, final int max) {
			this.min = min;
			this.max = max;
		}
	}

	/**
	 * Instantiates a new random object generator.
	 */
	public RandomObjectGenerator() {
		this(System.currentTimeMillis());
	}

	/**
	 * Instantiates a new data random object generator. This constructor allows you to pass a random seed to be able to reproduce test failures.
	 * 
	 * @param seed the seed to use for random generator.
	 */
	public RandomObjectGenerator(final long seed) {
		this.seed = seed;
		randomizer = new RandomDataGenerator(seed);
	}

	/**
	 * <p>
	 * Creates a new instance of the passed class and fills the object and every child object with random data.
	 * </p>
	 * 
	 * @param c the class.
	 * @param depth the object tree depth to fill.
	 * @return the filled object.
	 */
	public Object fill(final Class<?> c, final int depth) {
		setterPath.clear();
		return fill(c, depth, "", null);
	}

	/**
	 * Adds a range to respect for generating random float values. When a setter is found with the name <code>setterMethodName</code> the passed range will be
	 * used. This ways you can satisfy methods which validate their parameters.
	 * 
	 * @param setterMethodName the setter method name which uses validation.
	 * @param min the lower bound (inclusive).
	 * @param max the upper bound (exclusive).
	 */
	public void addFloatRange(final String setterMethodName, final float min, final float max) {
		floatRanges.put(setterMethodName, new FloatRange(min, max));
	}

	/**
	 * Adds a range to respect for generating random double values. When a setter is found with the name <code>setterMethodName</code> the passed range will be
	 * used. This ways you can satisfy methods which validate their parameters.
	 * 
	 * @param setterMethodName the setter method name which uses validation.
	 * @param min the lower bound (inclusive).
	 * @param max the upper bound (exclusive).
	 */
	public void addDoubleRange(final String setterMethodName, final double min, final double max) {
		doubleRanges.put(setterMethodName, new DoubleRange(min, max));
	}

	/**
	 * Adds a range to respect for generating random short values. When a setter is found with the name <code>setterMethodName</code> the passed range will be
	 * used. This ways you can satisfy methods which validate their parameters.
	 * 
	 * @param setterMethodName the setter method name which uses validation.
	 * @param min the lower bound (inclusive).
	 * @param max the upper bound (exclusive).
	 */
	public void addShortRange(final String setterMethodName, final short min, final short max) {
		shortRanges.put(setterMethodName, new ShortRange(min, max));
	}

	/**
	 * Adds a range to respect for generating random int values. When a setter is found with the name <code>setterMethodName</code> the passed range will be
	 * used. This ways you can satisfy methods which validate their parameters.
	 * 
	 * @param setterMethodName the setter method name which uses validation.
	 * @param min the lower bound (inclusive).
	 * @param max the upper bound (exclusive).
	 */
	public void addIntRange(final String setterMethodName, final int min, final int max) {
		intRanges.put(setterMethodName, new IntRange(min, max));
	}

	/**
	 * Adds a range to respect for generating random Long values. When a setter is found with the name <code>setterMethodName</code> the passed range will be
	 * used. This ways you can satisfy methods which validate their parameters.
	 * 
	 * @param setterMethodName the setter method name which uses validation.
	 * @param min the lower bound (inclusive).
	 * @param max the upper bound (exclusive).
	 */
	public void addLongRange(final String setterMethodName, final long min, final long max) {
		longRanges.put(setterMethodName, new LongRange(min, max));
	}

	/**
	 * Adds a length range to respect for generating random String values. When a setter is found with the name <code>setterMethodName</code> the passed range
	 * will be used. This ways you can satisfy methods which validate their parameters.
	 * 
	 * @param setterMethodName the setter method name which uses validation.
	 * @param min the lower bound (inclusive).
	 * @param max the upper bound (exclusive).
	 */
	public void addStringLengthRange(final String setterMethodName, final int min, final int max) {
		stringLengthRanges.put(setterMethodName, new StringLengthRange(min, max));
	}

	/**
	 * Tells {@link RandomObjectGenerator} which component type the list of the specified setter method uses. Due to type erasure the generic type of the list is not
	 * available anymore at runtime.
	 * 
	 * @param setterMethodName the setter method name with {@link List} parameter.
	 * @param componentType the component type of the list parameter.
	 */
	public void addListType(final String setterMethodName, final Class<?> componentType) {
		componentTypeMap.put(setterMethodName, componentType);
	}

	/**
	 * Adds key and value types of the map parameter of the specified setter. Works like {@link #addListType(String, Class)}.
	 * 
	 * @param setterMethodName the setter method name.
	 * @param keyType the key type.
	 * @param componentType the value type.
	 */
	public void addMapType(final String setterMethodName, final Class<?> keyType, final Class<?> componentType) {
		keyTypeMap.put(setterMethodName, keyType);
		componentTypeMap.put(setterMethodName, componentType);
	}

	/**
	 * Adds setters that must be ignored.
	 * 
	 * @param setterMethodName the setter method name to be ignored.
	 */
	public void addIgnoredSetter(final String setterMethodName) {
		ignoredSetters.add(setterMethodName);
	}

	/**
	 * Adds types that must not be instantiated (null will be passed).
	 * 
	 * @param clazz the type that will be ignored
	 */
	public void addIgnoredType(final Class clazz) {
		ignoredTypes.add(clazz);
	}

	/**
	 * Gets the seed used for the random generator.
	 * 
	 * @return the seed
	 */
	public long getSeed() {
		return seed;
	}

	/**
	 * Make all generated Strings to be trimmed.
	 * 
	 * @param trimStrings
	 */
	public void setTrimStrings(final boolean trimStrings) {
		this.trimStrings = trimStrings;
	}

	private Object fill(final Class<?> c, int depth, final String setterName, final Object initialValue) {

		try {
			depth--;
			if (c == float.class) {
				return handleFloat(setterName);
			} else if (c == Float.class) {
				return handleFloatObject(setterName);
			} else if (c == Double.class) {
				return handleDouble(setterName);
			} else if (c == double.class) {
				return handleDoubleObject(setterName);
			} else if (c == short.class) {
				return handleShort(setterName);
			} else if (c == int.class) {
				return handleInt(setterName);
			} else if (c == Integer.class) {
				return handleIntObject(setterName);
			} else if (c == long.class) {
				return handleLong(setterName);
			} else if (c == Long.class) {
				return handleLongObject(setterName);
			} else if (c == boolean.class) {
				return handleBoolean();
			} else if (c == Boolean.class) {
				return handleBooleanObject();
			} else if (c == String.class) {
				return handleString(setterName);
			} else if (Set.class.isAssignableFrom(c)) {
				// Set is one depth level on it's own. So we need one spare depth level for the set elements.
				if (depth > 1) { return handleCollection(depth, setterName, (initialValue == null ? new HashSet() : (Collection) initialValue)); }
			} else if (List.class.isAssignableFrom(c)) {
				// List is one depth level on it's own. So we need one spare depth level for the list elements.
				if (depth > 1) { return handleCollection(depth, setterName, (initialValue == null ? new ArrayList() : (Collection) initialValue)); }
			} else if (Collection.class.isAssignableFrom(c)) {
				// List is one depth level on it's own. So we need one spare depth level for the list elements.
				if (depth > 1) { return handleCollection(depth, setterName, (Collection) initialValue); }
			} else if (c.isArray()) {
				// Array is one depth level on it's own. So we need one spare depth level for the list elements.
				if (depth > 1) { return handleArray(depth, setterName); }
			} else if (c.isAssignableFrom(Map.class)) {
				// Map is one depth level on it's own. So we need one spare depth level for the list elements.
				if (depth > 1) { return handleMap(depth, setterName); }
			} else if (c.isEnum()) {
				return handleEnum(c);
			} else if (hasParameterlessContstructor(c)) {
				// complex type
				if (depth > 0) { return handleComplexType(c, depth, setterName); }
			} else {
				// unknown type
				throw new NotImplementedException("Don't know how to handle " + c);
			}
		} catch (final IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (final InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (final InstantiationException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	private Object handleComplexType(final Class<?> c, final int depth, final String setterName) throws InstantiationException, IllegalAccessException,
			InvocationTargetException {
		final Object o = c.newInstance();
		for (final Method m : c.getMethods()) {
			if (isSetter(m) && !ignoredSetters.contains(m.getName())) {
				final Class<?> paramClass = m.getParameterTypes()[0];
				if (ignoredTypes.contains(paramClass)) {
					continue;
				}
				setterPath.push(setterName);
				try {
					final Object defaultValue = getInitialValue(m, o, c);
					final Object value = fill(paramClass, depth, m.getName(), defaultValue);
					if (value != null) {
						m.invoke(o, value);
					}
				} finally {
					setterPath.pop();
				}
			}
		}
		return o;
	}

	private Double handleDoubleObject(final String setterMethodName) {
		return handleDouble(setterMethodName);
	}

	private double handleDouble(final String setterMethodName) {
		final DoubleRange floatRange = doubleRanges.get(setterMethodName);
		if (floatRange != null) {
			return randomizer.r.nextDouble() * (floatRange.max - floatRange.min) + floatRange.min;
		} else {
			return randomizer.r.nextDouble() * Double.MAX_VALUE;
		}
	}

	private short handleShort(final String setterMethodName) {
		final ShortRange shortRange = shortRanges.get(setterMethodName);
		if (shortRange != null) {
			return (short) (randomizer.r.nextInt(shortRange.max - shortRange.min) + shortRange.min);
		} else {
			return (short) randomizer.r.nextInt(Short.MAX_VALUE);
		}
	}

	private Float handleFloatObject(final String setterMethodName) {
		return handleFloat(setterMethodName);
	}

	private float handleFloat(final String setterMethodName) {
		final FloatRange floatRange = floatRanges.get(setterMethodName);
		if (floatRange != null) {
			return randomizer.r.nextFloat() * (floatRange.max - floatRange.min) + floatRange.min;
		} else {
			return randomizer.r.nextFloat() * Float.MAX_VALUE;
		}
	}

	private Boolean handleBooleanObject() {
		return randomizer.r.nextBoolean();
	}

	private boolean handleBoolean() {
		return randomizer.r.nextBoolean();
	}

	private Long handleLongObject(final String setterMethodName) {
		return handleLong(setterMethodName);
	}

	private long handleLong(final String setterMethodName) {
		final LongRange longRange = longRanges.get(setterMethodName);
		randomizer.r.nextLong();
		if (longRange != null) {
			return ((long) (randomizer.r.nextDouble() * (longRange.max - longRange.min))) + longRange.min;
		} else {
			return (long) (randomizer.r.nextDouble() * Long.MAX_VALUE);
		}
	}

	private Integer handleIntObject(final String setterMethodName) {
		return handleInt(setterMethodName);
	}

	private int handleInt(final String setterMethodName) {
		final IntRange intRange = intRanges.get(setterMethodName);
		if (intRange != null) {
			return randomizer.r.nextInt(intRange.max - intRange.min) + intRange.min;
		} else {
			return randomizer.r.nextInt(Integer.MAX_VALUE);
		}
	}

	private Object handleEnum(final Class<?> c) {
		final Object[] t = c.getEnumConstants();
		return t[randomizer.getInt(t.length)];
	}

	private Object[] handleArray(final int depth, final String setterName) throws InstantiationException, IllegalAccessException {
		final Collection collection = handleCollection(depth, setterName, new ArrayList());
		if (collection != null) {
			final Class componentType = componentTypeMap.get(setterName);
			// we don't need to check componentType!=null, handleList() did this already.
			return collection.toArray((Object[]) Array.newInstance(componentType, collection.size()));
		} else {
			return null;
		}
	}

	private Object handleMap(final int depth, final String setterName) {
		final Map map = new HashMap();
		final Class keyType = keyTypeMap.get(setterName);
		final Class componentType = componentTypeMap.get(setterName);
		if (keyType == null || componentType == null) {
			System.out.println("No Map-Type settings found for : '" + dumpSetterPath(setterName) + "'");
			return null;
		}

		final int mapSize = randomizer.getInt(MAX_LIST_LENGTH);
		for (int i = 0; i < mapSize; i++) {
			map.put(fill(keyType, depth, setterName, null), fill(componentType, depth, setterName, null));
		}

		return map;
	}

	private String handleString(final String setterMethodName) {
		final StringLengthRange stringLengthRange = stringLengthRanges.get(setterMethodName);

		final int minLength;
		final int maxLength;

		if (stringLengthRange != null) {
			minLength = stringLengthRange.min;
			maxLength = stringLengthRange.max;
		} else {
			minLength = DEFAULT_MIN_STRING_LENGTH;
			maxLength = DEFAULT_MAX_STRING_LENGTH;
		}

		String randomString;
		do {
			randomString = randomizer.getString(minLength, maxLength);
		} while (randomString.trim().isEmpty());
		if (trimStrings) {
			randomString = randomString.trim();
		}
		return randomString;
	}

	private Collection handleCollection(final int depth, final String setterName, final Collection defaultValue) throws InstantiationException,
			IllegalAccessException {
		final Collection collection;
		if (defaultValue != null) {
			collection = clone(defaultValue);
		} else {
			collection = new ArrayList();
		}

		final Class componentType = componentTypeMap.get(setterName);
		if (componentType == null) {
			System.out.println("No List-Type settings found for : '" + dumpSetterPath(setterName) + "'");
			return null;
		}
		int listSize = randomizer.getInt(MAX_LIST_LENGTH);
		listSize = Math.max(listSize, MIN_LIST_LENGTH);
		for (int i = 0; i <= listSize; i++) {
			collection.add(fill(componentType, depth, setterName, null));
		}

		return collection;
	}

	/**
	 * Invokes the clone() method on any object (even it it's private). Any Exception is treated as unrecoverable.
	 */
	public static <T> T clone(final T object) {
		if (!(object instanceof Cloneable)) { throw new IllegalArgumentException(object.getClass() + " is not Cloneable"); }
		try {
			Object clone = null;
			// Use reflection, because there is no other way
			final Method method = object.getClass().getMethod("clone");
			clone = method.invoke(object);
			return (T) clone; // NOSONAR
		} catch (final Exception e) {
			throw new IllegalArgumentException(object + " caused a problem while being cloned", e);
		}
	}

	private String dumpSetterPath(final String topStackElement) {
		return StringUtils.join(setterPath, "().") + "()." + topStackElement;
	}

	private boolean hasParameterlessContstructor(final Class<?> c) {
		for (final Constructor<?> constructor : c.getConstructors()) {
			if (constructor.getParameterTypes().length == 0) { return true; }
		}
		return false;
	}

	private boolean isSetter(final Method m) {
		final String name = m.getName();
		final int parameterCount = m.getParameterTypes().length;
		return parameterCount == 1 && name.startsWith(SETTER_PREFIX);
	}

	private Object getInitialValue(final Method setter, final Object o, final Class<?> clazz) {
		Object initialValue = null;

		final String setterName = setter.getName();
		String fieldName = null;
		if (setterName.startsWith(SETTER_PREFIX)) {
			fieldName = setterName.substring(SETTER_PREFIX.length());
		}
		if (fieldName != null) {
			Method getter = null;
			// get...
			try {
				getter = clazz.getMethod("get" + fieldName, (Class<?>[]) null);
			} catch (final Exception e) {
				// do nothing
			}

			// is...
			try {
				getter = clazz.getMethod("is" + fieldName, (Class<?>[]) null);
			} catch (final SecurityException e) {
				// do nothing
			} catch (final NoSuchMethodException e) {
				// do nothing
			}

			if (getter != null) {
				try {
					initialValue = getter.invoke(o, (Object[]) null);
				} catch (final Exception e) {
					// do nothing
				}
			}
		}

		return initialValue;
	}

}
