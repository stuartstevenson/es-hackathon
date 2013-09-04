create table properties as
  select
/*+parallel(p, 7)*/
    p.postcode1 as outcode,
    p.postcode2 as incode,
    p.id,
    p.price,
    p.bedrooms,
    p.first_listing_date,
    P.SUMMARY,
    P.DESCRIPTION,
    PT.NAME property_type,
    PST.NAME property_sub_type,
    (select listagg(F.DESCRIPTION, '^') WITHIN GROUP (ORDER BY NULL) from FEATURE F where property_id=p.id) features,
    P.LONGITUDE,
    P.LATITUDE,
    P.MAIN_IMAGE_1,
    P.MAIN_IMAGE_2,
    P.MAIN_IMAGE_3,
    P.NO_OF_IMAGES,
    P.NO_OF_FLOOR_PLANS,
    P.NO_OF_VIRTUAL_TOURS
  from
      property p,
      property_type pt,
      property_sub_type pst
  where
    P.PROP_TYPE_ID=pt.id and
    P.PROP_SUB_ID=pst.id and
    P.postcode_id is not null and
                         P.TRANS_TYPE_ID=1 and
                         P.FIRST_LISTING_PROPERTY_ID = p.id and
                         P.PUBLISHED_FLAG='T' and
                         P.PROP_TYPE_ID in (1,2,3) and
                         rownum <= 25000
  order by id desc;

select * from properties
where main_image_1 is not null
                          and main_image_2 is not null
                                                  and main_image_3 is not null;