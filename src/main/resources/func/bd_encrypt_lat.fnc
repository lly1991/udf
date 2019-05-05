create or replace function daily.bd_encrypt_lat(gcjLat number,gcjLong number)

return number

as

x_pi constant number := 3.14159265358979324 * 3000.0 / 180.0;

x number;
y number;
z number;
theta number;
bdLat number;

begin

x := gcjLong;
y := gcjLat;
z := sqrt(x * x + y * y) + 0.00002 * sin(y * x_pi);
theta := atan2(y, x) + 0.000003 * cos(x * x_pi);
bdLat := z * sin(theta) + 0.006;

return bdlat;

end;
/

