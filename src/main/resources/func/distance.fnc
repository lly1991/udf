CREATE OR REPLACE FUNCTION daily.distance(lon1 in number,
                                    lat1 in number,
                                    lon2 in number,
                                    lat2 in number) return number as
  a_2d    number;
  e_2d    number;
  h_2d    number;
  deg2rad number;
  --rad2deg number;

  x_rads number;
  y_rads number;

  n_2ds       number;
  x_2d        number;
  y_2d        number;
  z_2d        number;
  x_radm      number;
  y_radm      number;
  n_2dm       number;
  x_2d_mark   number;
  y_2d_mark   number;
  z_2d_mark   number;
  curdistance number;

begin
  a_2d    := 6378137;
  e_2d    := 0.00669438;
  h_2d    := 15;
  deg2rad := 0.01745329252;
  --rad2deg := 57.2957795129;
  if (lon1 is null or lat1 is null or lon2 is null or lat2 is null ) then
    return 0;
  end if;

  x_rads := Abs(lon1) * deg2rad;
  y_rads := Abs(lat1) * deg2rad;

  n_2ds := a_2d / sqrt(1 - e_2d * Sin(y_rads) * Sin(y_rads));

  x_2d := (n_2ds + h_2d) * Cos(y_rads) * Cos(x_rads);
  y_2d := (n_2ds + h_2d) * Cos(y_rads) * Sin(x_rads);
  z_2d := (n_2ds * (1 - e_2d) + h_2d) * Sin(y_rads);

  x_radm := Abs(lon2) * deg2rad;
  y_radm := Abs(lat2) * deg2rad;

  n_2dm := a_2d / sqrt(1 - e_2d * Sin(y_radm) * Sin(y_radm));

  x_2d_mark := (n_2dm + h_2d) * Cos(y_radm) * Cos(x_radm);
  y_2d_mark := (n_2dm + h_2d) * Cos(y_radm) * Sin(x_radm);
  z_2d_mark := (n_2dm * (1 - e_2d) + h_2d) * Sin(y_radm);

  curdistance := (x_2d_mark - x_2d) * (x_2d_mark - x_2d) +
                 (y_2d_mark - y_2d) * (y_2d_mark - y_2d) +
                 (z_2d_mark - z_2d) * (z_2d_mark - z_2d);
  curdistance := sqrt(curdistance);

  return curdistance;

end;
/

