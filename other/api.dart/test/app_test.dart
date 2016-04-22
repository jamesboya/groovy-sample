import 'package:test/test.dart';
import 'package:http/http.dart' as http;

import 'dart:io';
import 'dart:convert' show JSON;

main() {
    test("regex", () {
        var pattern = new RegExp(r"abc");
        expect(pattern.firstMatch("abc").group(0), equals("abc"));
    });

    test("http request", () async {
        var url = "https://www.google.com";
        var response = await http.get(url);

        expect(1, isNotNull);
    });

    test("json decode", () {
        var jsonString = '''
            [
                {"score": 40},
                {"score": 80}
            ]''';

        var scores = JSON.decode(jsonString);

        expect(scores, isList);
    });
}