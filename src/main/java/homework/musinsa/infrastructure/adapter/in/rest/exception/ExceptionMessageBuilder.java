package homework.musinsa.infrastructure.adapter.in.rest.exception;

public class ExceptionMessageBuilder {

	public static class EntityNotFoundException {

		public static String message(final Class<?> entityClass) {
			return String.format("Entity of type '%s' is not found", entityClass.getSimpleName());
		}

		public static String message(final Class<?> entityClass, final String prop1, final Object val1) {
			return String.format("Entity of type '%s' is not found, '%s':'%s'. ", entityClass.getSimpleName(), prop1, val1);
		}

		public static String message(final Class<?> entityClass, final String prop1, final Object val1, final String prop2, final Object val2) {
			return String.format("Entity of type '%s' is not found, '%s':'%s', '%s':'%s'. ", entityClass.getSimpleName(), prop1, val1, prop2, val2);
		}
	}

}
