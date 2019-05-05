create or replace function daily.get
_dstnc
(lon1 in number,
                                     lat1 in number,
                                     lon2 in number,
                                     lat2 in number) return number is
  Result number;
  r  constant number := 6378137;
  pi constant number := acos(-1);

begin
  Result := r * acos(cos(lat1) * cos(lat2) * cos((lon1 - lon2)) +
                     sin(lat1) * sin(lat2)) / 180 * pi;
  return(Result);
exception
  when others then
    return 0;
end get_dstnc;
/
