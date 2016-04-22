part of app;

class PlaceDao extends BasicDao {
    final String _table = "place";

    PlaceDao(DataSource ds): super(ds);

    void add(Place place) async {
        await execute((collection) async {
            collection.insert(place.toMap());
        });
    }

    List<Place> findByName(String name) async {
        var dbRows = await query((collection) async {
            return await collection.find(where.eq("name", name)).toList();
        });
        
        var results = dbRows.map((row) {
            return new Place.fromMap(row);
        });

        return results;
    }

    void deleteByName(String name) async {
        await execute((collection) async {
            collection.remove(where.eq("name", name));
        });
    }
}

class Place extends Entity {
    String name;
    String address;
    String image;
    String summary;
    String traffic;

    Place(this.name, this.address, this.image, this.summary, this.traffic);

    Place.fromMap(map): super.fromMap(map);
}