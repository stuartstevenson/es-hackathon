select
/*+parallel(p, 7)*/
  p.postcode1 as outcode,
  p.postcode2 as incode,
  p.id,
  p.price,
  p.bedrooms,
  p.first_listing_date,
  P.SUMMARY,
  PT.NAME,
  PST.NAME,
  listagg(F.DESCRIPTION, '^') WITHIN GROUP (ORDER BY NULL) features,
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
    property_sub_type pst,
    feature f
where
  p.id=f.property_id and
  P.PROP_TYPE_ID=pt.id and
  P.PROP_SUB_ID=pst.id and
  P.postcode_id is not null and
                       P.TRANS_TYPE_ID=1 and
                       P.FIRST_LISTING_PROPERTY_ID = p.id and
                       P.PUBLISHED_FLAG='T' and
                       P.PROP_TYPE_ID in (1,2,3) and
                       rownum <= 300000
group by
  p.postcode1,
  p.postcode2,
  p.id,
  p.price,
  p.bedrooms,
  p.first_listing_date,
  P.SUMMARY,
  PT.NAME,
  PST.NAME,
  P.LONGITUDE,
  P.LATITUDE,
  P.MAIN_IMAGE_1,
  P.MAIN_IMAGE_2,
  P.MAIN_IMAGE_3,
  P.NO_OF_IMAGES,
  P.NO_OF_FLOOR_PLANS,
  P.NO_OF_VIRTUAL_TOURS
order by id desc;