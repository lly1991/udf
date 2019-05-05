create or replace function daily.deltalat(lat in number, long in number)

return number

as

a constant number := 6378245.0;
ee constant number := 0.00669342162296594323;
PI constant number := 3.14159265358979324;

dLat number;
deltaLatFunction number;
radLat number;
magic number;
sqrtMagic number;

x number;
y number;

begin

x := long - 105.0;
y := lat - 35.0;

dLat := -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * sqrt(abs(x))
  + (20.0 * sin(6.0 * x * PI) + 20.0 * sin(2.0 * x * PI)) * 2.0 / 3.0
  + (20.0 * sin(y * PI) + 40.0 * sin(y / 3.0 * PI)) * 2.0 / 3.0
  + (160.0 * sin(y / 12.0 * PI) + 320 * sin(y * PI / 30.0)) * 2.0 / 3.0;

radLat := lat / 180.0 * PI;
magic := sin(radLat);
magic := 1 - ee * magic * magic;
sqrtMagic := sqrt(magic);
deltaLatFunction := (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * PI);

return deltaLatFunction;

end;
/

