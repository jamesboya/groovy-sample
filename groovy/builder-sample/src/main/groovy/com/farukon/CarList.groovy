package com.farukon

// trait for enhancing class with builder ability
trait SimpleBuilder {
    def build(Closure cl) {
        cl.delegate = this
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        cl.call()

        this
    }
}

// CarInfo
class CarInfo implements SimpleBuilder {
    String name
    String maker

    @Override
    String toString() {
        "$name ($maker)"
    }
}

// Cars
class Cars implements SimpleBuilder {
    def carList = []

    def methodMissing(String name, args) {
        if (name == "car") {
            if (args[0] instanceof Closure) {
                carList += (new CarInfo()).build(args[0])
            }
        } else {
            throw new Exception("not supported method: $name")
        }
    }

    @Override
    String toString() {
        carList.toString()
    }
}

// test Cars builder
def cars = new Cars().build() {
    car { name = 'MITSUBISHI TRITON'
          maker = 'MITSUBISHI' }

    car { name = 'SKODA FABIA ELEGANCE'
          maker = 'SKODA' }

    car { name = 'MINI COOPER'
          maker = 'MINI' }
}

cars.each { car -> println car }