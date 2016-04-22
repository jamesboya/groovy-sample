part of app;

abstract class Serializable {
    Map toMap() { 
        Map map = new Map();

        InstanceMirror instMirror = reflect(this);
        ClassMirror clsMirror = instMirror.type;
        var varDecls = clsMirror.declarations.values.where((dm) => dm is VariableMirror);
        varDecls.forEach((dm) {
            var fieldName = MirrorSystem.getName(dm.simpleName);
            var fieldValue = instMirror.getField(dm.simpleName).reflectee;
            map[fieldName] = fieldValue;
        });

        return map;
    }

    fromMap(Map map) {
        InstanceMirror instMirror = reflect(this);
        ClassMirror clsMirror = instMirror.type;
        var varDecls = clsMirror.declarations.values.where((dm) => dm is VariableMirror);
        varDecls.forEach((dm) {
            var fieldName = MirrorSystem.getName(dm.simpleName);
            if (map.containsKey(fieldName)) {
                instMirror.setField(new Symbol(fieldName), map[fieldName]);
            }
        });
    }
}