import 'package:test/test.dart';
import 'package:app/app.dart';

main() async {
    test("basic CURD", () async {
        DataSource ds = new DataSource("place", new Config());

        PlaceDao placeDao = new PlaceDao(ds);

        // remove all record where name = "place name"
        await placeDao.deleteByName("place name");

        // ensure the number of result should be 0
        List<Place> placeList = await placeDao.findByName("place name");
        expect(placeList.length, equals(0));

        // insert one record
        await placeDao.add(new Place(
            "place name", 
            "address", 
            "http://example.com/foo.jpg", 
            "a good place",
            "just go there"));

        placeList = await placeDao.findByName("place name");
        expect(placeList.length, equals(1));
    });
}