package errors

sealed class CarErrors(val message: String) {
    class carNotValid(message: String) : CarErrors(message)
    class carNotFound(message: String) : CarErrors(message)
    class CarNotSaved(message: String) : CarErrors(message)


}
