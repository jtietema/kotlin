// SHOULD_FAIL_WITH: All inheritors must be nested objects of the class itself and may not inherit from other classes or interfaces

sealed class <caret>X {
    class A : X()
}

class B : X()