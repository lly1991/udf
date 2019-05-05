create or replace function daily.gcj_encrypt_lat(
wgslat number,wgslong number)

return number

as

begin
if (wgslat < 0.8293 or wgslat > 55.8271) then
  return null;
else
  return wgslat + deltalat(wgsLat,wgslong);
end if;

end;
/

