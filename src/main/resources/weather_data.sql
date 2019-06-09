INSERT INTO PUBLIC.SERVICE (ID,NAME) VALUES (
1,'World Weather Online');
INSERT INTO PUBLIC.SERVICE (ID,NAME) VALUES (
2,'AccuWeather.com');

INSERT INTO PUBLIC.CITY (ID,NAME) VALUES (
1,'Екатеринбург');
INSERT INTO PUBLIC.CITY (ID,NAME) VALUES (
2,'Москва');
INSERT INTO PUBLIC.CITY (ID,NAME) VALUES (
3,'Челябинск');

INSERT INTO PUBLIC.SERVICE_CITY (SERVICE_ID,CITY_ID,URL) VALUES (
1,1,'http://api.worldweatheronline.com/premium/v1/weather.ashx?key=3700b5ba25794185a6d54121190406&q=Ekaterinburg&format=json&fx=no&mca=no');
INSERT INTO PUBLIC.SERVICE_CITY (SERVICE_ID,CITY_ID,URL) VALUES (
1,2,'http://api.worldweatheronline.com/premium/v1/weather.ashx?key=3700b5ba25794185a6d54121190406&q=Moscow&format=json&fx=no&mca=no');
INSERT INTO PUBLIC.SERVICE_CITY (SERVICE_ID,CITY_ID,URL) VALUES (
1,3,'http://api.worldweatheronline.com/premium/v1/weather.ashx?key=3700b5ba25794185a6d54121190406&q=Chelyabinsk&format=json&fx=no&mca=no');
INSERT INTO PUBLIC.SERVICE_CITY (SERVICE_ID,CITY_ID,URL) VALUES (
2,1,'http://dataservice.accuweather.com/currentconditions/v1/1-295863_1_AL?apikey=bopAMwG1ZSYlgP4VsffODxHp1B4i8E06&language=ru&details=true');
INSERT INTO PUBLIC.SERVICE_CITY (SERVICE_ID,CITY_ID,URL) VALUES (
2,2,'http://dataservice.accuweather.com/currentconditions/v1/294021?apikey=bopAMwG1ZSYlgP4VsffODxHp1B4i8E06&language=ru&details=true');

INSERT INTO PUBLIC.SERVICE_CITY (SERVICE_ID,CITY_ID,URL) VALUES (
2,3,'http://dataservice.accuweather.com/currentconditions/v1/292332?apikey=bopAMwG1ZSYlgP4VsffODxHp1B4i8E06&language=ru&details=true');
