interface Test {
    public open fun test()
    public open val testProp : Int
}

class SomeTest : Test {
    val hello = 12
    // Some comment
    <caret>
    /*
        Some another comment
    */
    fun some() {

    }
}
