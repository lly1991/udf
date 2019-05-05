create or replace function daily.bd_encrypt_long(gcjLat number,gcjLong number)

return number

as

x_pi constant number := 3.14159265358979324 * 3000.0 / 180.0;

x number;
y number;
z number;
theta number;
bdLong number;

begin


x := gcjLong;
y := gcjLat;
z := sqrt(x * x + y * y) + 0.00002 * sin(y * x_pi);
theta := atan2(y, x) + 0.000003 * cos(x * x_pi);
bdLong := z * cos(theta) + 0.0065;

return bdlong;

end;
/

