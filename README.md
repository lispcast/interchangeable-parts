# interchangeable-parts

A Clojure library that implements the Interchangeable Parts protocol
pattern. It applies it to the problem of starting and stopping
services in a system.

When we build systems, there are often things that need to be
initialized in a certain order. For instance, we want to connect to
the database before we open our web server port to listen for
connections. And then when we shut down, we want to wait for all
connections to close before we disconnect from the database.

This is similar in spirit to the [Component] system built by Stuart
Sierra, but much easier. It's suitable as an example of applying the
Interchangeable Parts pattern, but if you need something
production-ready, please check out Component.

[Component]: https://github.com/stuartsierra/component

Part of the [*PurelyFunctional.tv Online Mentoring*][ment] program.

[ment]: http://www.purelyfunctional.tv/mentoring

## License

[![CC0](http://i.creativecommons.org/p/zero/1.0/88x31.png)](http://creativecommons.org/publicdomain/zero/1.0/)

To the extent possible under law, the person who associated CC0 with
this work has waived all copyright and related or neighboring rights
to the code in this repository.

See the `LICENSE` file for more information.
