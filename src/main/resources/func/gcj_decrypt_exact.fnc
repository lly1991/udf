create or replace function daily.gcj_decrypt_exact(
  gcjLat number,
  gcjLong number
)

return varchar2

as

initDelta constant number := 0.01;
threshold constant number := 0.000000001;

dLat number;
dLong number;
mLat number;
pLat number;
mLong number;
pLong number;

i int;

wgsLat number;
wgsLong number;

begin

dLat := initDelta;
dLong := initDelta;

mLat := gcjLat - dLat;
mLong := gcjLong - dLong;
pLat := gcjLat + dLat;
pLong := gcjLong + dLong;

i := 0;
loop
wgsLat := (mLat + pLat) / 2;
wgsLong := (mLong + pLong) / 2;
dLat := gcj_encrypt_lat(wgsLat, wgsLong) - gcjLat;
dLong := gcj_encrypt_long(wgsLat, wgsLong) - gcjLong;

if ((abs(dLat) < threshold) and (abs(dLong) < threshold)) then
  exit;
end if;

if (dLat > 0) then
  pLat := wgsLat;
else
  mLat := wgsLat;
end if;

if (dLong > 0) then
  pLong := wgsLong;
else
  mLong := wgsLong;
end if;

i := i + 1;

if (i > 10000) then
  exit;
end if;

end loop;

return to_char(wgsLong)||':'||to_char(wgsLat);

end;
/

