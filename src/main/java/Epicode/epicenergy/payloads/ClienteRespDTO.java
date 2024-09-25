package Epicode.epicenergy.payloads;

public class ClienteRespDTO {
    private Long id;

    public ClienteRespDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
