create or replace function daily.bd_decrypt_long(bdLat number,bdLong number)

return number

as

x_pi constant number := 3.14159265358979324 * 3000.0 / 180.0;

x number;
y number;
z number;
theta number;
gcjlong number;

begin

x := bdLong - 0.0065;
y := bdLat - 0.006;
z := sqrt(x * x + y * y) - 0.00002 * sin(y * x_pi);
theta := atan2(y, x) - 0.000003 * cos(x * x_pi);
gcjLong := z * cos(theta);

return gcjlong;

end;
/

