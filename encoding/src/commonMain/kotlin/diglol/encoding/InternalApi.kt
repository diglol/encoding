package diglol.encoding

@Target(
  AnnotationTarget.CLASS,
  AnnotationTarget.PROPERTY,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.TYPEALIAS
)
@RequiresOptIn(level = RequiresOptIn.Level.ERROR)
annotation class InternalApi
