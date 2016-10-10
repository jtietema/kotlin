// SHOULD_FAIL_WITH: All inheritors must be nested objects of the class itself and may not inherit from other classes or interfaces

interface I

sealed class <caret>X {
    object A : X(), I
    object B : X()
}