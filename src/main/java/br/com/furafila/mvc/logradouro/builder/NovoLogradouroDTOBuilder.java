package br.com.furafila.mvc.logradouro.builder;

import br.com.furafila.mvc.logradouro.dto.NovoLogradouroDTO;

public class NovoLogradouroDTOBuilder {

	private Integer postalCode;
	private String address;
	private String addressType;
	private String city;
	private String postalAbbreviation;
	private String district;
	private Double latitude;
	private Double longitude;

	public NovoLogradouroDTOBuilder postalCode(Integer postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	public NovoLogradouroDTOBuilder address(String address) {
		this.address = address;
		return this;
	}

	public NovoLogradouroDTOBuilder addressType(String addressType) {
		this.addressType = addressType;
		return this;
	}

	public NovoLogradouroDTOBuilder city(String city) {
		this.city = city;
		return this;
	}

	public NovoLogradouroDTOBuilder postalAbbreviation(String postalAbbreviation) {
		this.postalAbbreviation = postalAbbreviation;
		return this;
	}

	public NovoLogradouroDTOBuilder district(String district) {
		this.district = district;
		return this;
	}

	public NovoLogradouroDTOBuilder latitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}

	public NovoLogradouroDTOBuilder longitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}

	public NovoLogradouroDTO build() {
		NovoLogradouroDTO novoLogradouroDTO = new NovoLogradouroDTO();
		novoLogradouroDTO.setPostalCode(postalCode.longValue());
		novoLogradouroDTO.setAddress(address);
		novoLogradouroDTO.setAddressType(addressType);
		novoLogradouroDTO.setCity(city);
		novoLogradouroDTO.setPostalAbbreviation(postalAbbreviation);
		novoLogradouroDTO.setDistrict(district);
		novoLogradouroDTO.setLatitude(latitude);
		novoLogradouroDTO.setLongitude(longitude);

		return novoLogradouroDTO;
	}

}
