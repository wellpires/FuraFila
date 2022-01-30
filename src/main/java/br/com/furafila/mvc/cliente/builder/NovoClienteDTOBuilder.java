package br.com.furafila.mvc.cliente.builder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import br.com.furafila.mvc.cliente.dto.NovoClienteDTO;

public class NovoClienteDTOBuilder {

	private String name;
	private LocalDate birthday;
	private Long cpf;
	private String gender;
	private Long homePhone;
	private Long commercialPhone;
	private Long mobilePhone;
	private String email;
	private String addressMoreInfo;
	private Integer houseNumber;
	private Integer doorNumber;
	private Integer postalCode;
	private Long idLogin;
	private Long idImage;

	public NovoClienteDTOBuilder name(String name) {
		this.name = name;
		return this;
	}

	public NovoClienteDTOBuilder birthday(Date birthday) {

		LocalDate birthDayLocalDate = null;
		if (Objects.nonNull(birthday)) {
			birthDayLocalDate = LocalDate.from(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
		this.birthday = birthDayLocalDate;
		return this;
	}

	public NovoClienteDTOBuilder cpf(Long cpf) {
		this.cpf = cpf;
		return this;
	}

	public NovoClienteDTOBuilder gender(Character gender) {

		String genderString = null;
		if (Objects.nonNull(gender)) {
			genderString = gender.toString();
		}

		this.gender = genderString;
		return this;
	}

	public NovoClienteDTOBuilder homePhone(Long homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public NovoClienteDTOBuilder commercialPhone(Long commercialPhone) {
		this.commercialPhone = commercialPhone;
		return this;
	}

	public NovoClienteDTOBuilder mobilePhone(Long mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public NovoClienteDTOBuilder email(String email) {
		this.email = email;
		return this;
	}

	public NovoClienteDTOBuilder addressMoreInfo(String addressMoreInfo) {
		this.addressMoreInfo = addressMoreInfo;
		return this;
	}

	public NovoClienteDTOBuilder houseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
		return this;
	}

	public NovoClienteDTOBuilder doorNumber(Integer doorNumber) {
		this.doorNumber = doorNumber;
		return this;
	}

	public NovoClienteDTOBuilder postalCode(Integer postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	public NovoClienteDTOBuilder idLogin(Integer idLogin) {
		this.idLogin = idLogin.longValue();
		return this;
	}

	public NovoClienteDTOBuilder idImage(Integer idImage) {
		this.idImage = idImage.longValue();
		return this;
	}

	public NovoClienteDTO build() {
		NovoClienteDTO novoClienteDTO = new NovoClienteDTO();
		novoClienteDTO.setName(name);
		novoClienteDTO.setBirthday(birthday);
		novoClienteDTO.setCpf(cpf);
		novoClienteDTO.setGender(gender);
		novoClienteDTO.setHomePhone(homePhone);
		novoClienteDTO.setCommercialPhone(commercialPhone);
		novoClienteDTO.setMobilePhone(mobilePhone);
		novoClienteDTO.setEmail(email);
		novoClienteDTO.setAddressMoreInfo(addressMoreInfo);
		novoClienteDTO.setHouseNumber(houseNumber);
		novoClienteDTO.setDoorNumber(doorNumber);
		novoClienteDTO.setPostalCode(postalCode);
		novoClienteDTO.setIdLogin(idLogin);
		novoClienteDTO.setIdImage(idImage);

		return novoClienteDTO;
	}

}
