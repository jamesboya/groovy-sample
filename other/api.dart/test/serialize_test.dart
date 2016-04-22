import 'package:test/test.dart';
import 'package:app/app.dart';

main() {
    test("test fromMap", () {
        Map<String, String> map = {
            "name": "fooName",
            "address": "fooAddress",
            "image": "fooImage",
            "summary": "fooSummary",
            "traffic": "fooTrafiic"
        };

        Place place = new Place.fromMap(map);

        expect(place.name, "fooName");
        expect(place.address, "fooAddress");
        expect(place.image, "fooImage");
        expect(place.summary, "fooSummary");
        expect(place.traffic, "fooTrafiic");
    });

    test("test toMap", () {
        Place place = new Place("fooName", 
            "fooAddress", "fooImage", "fooSummary", "fooTrafiic");

        Map<String, Object> map = place.toMap();

        expect(map["name"], "fooName");
        expect(map["address"], "fooAddress");
        expect(map["image"], "fooImage");
        expect(map["summary"], "fooSummary");
        expect(map["traffic"], "fooTrafiic");
    });
}