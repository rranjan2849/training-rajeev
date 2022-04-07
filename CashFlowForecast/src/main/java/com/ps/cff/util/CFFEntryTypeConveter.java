package com.ps.cff.util;

import javax.persistence.AttributeConverter;

import com.ps.cff.entity.CFFEntryType;

/*
 * CFF Entry Type Converter Class
 */
public class CFFEntryTypeConveter implements AttributeConverter<CFFEntryType, String>{

	@Override
	public String convertToDatabaseColumn(CFFEntryType attribute) {
		switch (attribute) {
		case RECEIVABLE:
			return "R";
		case PAYABLE:
			return "P";
		default: 
			throw new IllegalArgumentException("CFFEntry Type"+attribute);
		}	
	}

	@Override
	public CFFEntryType convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "R":
			return CFFEntryType.RECEIVABLE;
		case "P":
			 return CFFEntryType.PAYABLE;
		default:	
			 throw new IllegalArgumentException("CFFEntry Type" + dbData);

		}
	}
}
