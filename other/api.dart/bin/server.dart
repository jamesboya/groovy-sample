// https://www.dartlang.org/tools/pub/package-layout.html
// https://www.dartlang.org/tools/pub/get-started.html
// https://www.dartlang.org/dart-by-example/

import 'package:redstone/redstone.dart' as app;
import 'package:di/di.dart';
// import 'package:logging/logging.dart';

import 'package:app/app.dart';

main() async {
    // Logger.root.level = Level.ALL;
    // Logger.root.onRecord.listen((LogRecord rec) {
    //     print('${rec.level.name}: ${rec.time}: ${rec.message}');
    // });

    Config config = new Config();
    Map<String, String> environment = config.environment;

    Module module = new Module()
        ..bind(Config, toValue: config);
	app.addModule(module);

	app.setupConsoleLog();
	app.start(address: config.host, port: config.port);
}
