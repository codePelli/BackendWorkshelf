package ejercicios.enums;

public enum ReservationStatus {
    AVAILABLE(0),
    RESERVED(1);

    private final int code;

    ReservationStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}