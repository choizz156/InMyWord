package inmyword.server.user.entity;

public enum UserStatus {
	ACTIVE("활동 중"), WITHDRWAL("탈퇴");

	private String status;

	UserStatus(String status) {
		this.status = status;
	}
}
