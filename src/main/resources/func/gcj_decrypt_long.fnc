create or replace function daily.gcj_
decrypt_long(
gcjlat number,gcjlong number)

return number

as

begin
if (gcjlong < 72.004 or gcjlong > 137.8347) then
  return null;
else
  return gcjlong-deltalong(gcjLat,gcjlong);
end if;

end;
/

