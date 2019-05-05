create or replace function daily.gcj_decrypt_lat
(gcjlat number,gcjlong number)


return number

as

begin
if (gcjlat < 0.8293 or gcjlat > 55.8271) then
  return null;
else
  return gcjlat - deltalat(gcjLat,gcjlong);
end if;

end;
/

