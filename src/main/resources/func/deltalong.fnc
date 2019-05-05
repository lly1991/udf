create or replace function daily.deltalong(lat in number, long in number)

return number

as

a constant number := 6378245.0;
ee constant number := 0.00669342162296594323;
PI constant number := 3.14159265358979324;

dLong number;
deltaLongFunction number;
radLat number;
magic number;
sqrtMagic number;

x number;
y number;

begin

x := long - 105.0;
y := lat - 35.0;

dLong := 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * sqrt(abs(x))
  + (20 * SIN(6 * x * PI) + 20 * SIN(2 * x * PI)) * 2 / 3
  + (20 * SIN(x * PI) + 40 * SIN(x / 3 * PI)) * 2 / 3
  + (150.0 * sin(x / 12.0 * PI) + 300.0 * sin(x / 30.0 * PI)) * 2.0 / 3.0;

radLat := lat / 180.0 * PI;
magic := sin(radLat);
magic := 1 - ee * magic * magic;
sqrtMagic := sqrt(magic);
deltaLongFunction := (dLong * 180.0) / (a / sqrtMagic * cos(radLat) * PI);

return deltalong;

end;
/

