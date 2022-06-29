package diglol.encoding

internal inline fun ignoreNpe(block: () -> Unit) =
  try {
    block()
  } catch (e: NullPointerException) {
    // ignore http://tools.android.com/tech-docs/unit-testing-support
  }
