<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="s" uri="http://www.springframework.org/tags"%> --%>
<%-- <%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%> --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

home.jsp
<p>
Now: <fmt:formatDate value="${now}" pattern="hh:mm:ss a MMM d, yyyy" />

<p>
Games: ${games}
