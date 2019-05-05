create or replace function daily.gcj_encrypt_long(wgslat number,wgslong number)

return number

as

begin
if (wgslong < 72.004 or wgslong > 137.8347) then
  return null;
else
  return wgslong + deltalong(wgsLat,wgslong);
end if;

end;
/

