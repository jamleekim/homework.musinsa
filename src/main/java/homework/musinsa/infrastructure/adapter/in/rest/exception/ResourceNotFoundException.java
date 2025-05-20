package homework.musinsa.infrastructure.adapter.in.rest.exception;

public abstract class ResourceNotFoundException extends RuntimeException {

	/**
	 * 일반적인 메시지를 받아 예외를 생성합니다.
	 * @param message 예외 메시지
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

	/**
	 * 리소스명, 필드명, 필드값을 기반으로 표준화된 예외 메시지를 생성합니다.
	 * @param resourceName 찾지 못한 리소스의 이름 (예: "BrandEntity", "Product")
	 * @param fieldName 검색에 사용된 필드의 이름 (예: "ID", "name")
	 * @param fieldValue 검색에 사용된 필드의 값
	 */
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s에서 %s '%s'을(를) 찾을 수 없습니다.", resourceName, fieldName, fieldValue));
	}

}