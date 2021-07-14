package com.inditex.pricing.convert;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.money.Monetary;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.javamoney.moneta.Money;

/**
 * Clase conversión de tipos de datos para Hibernate. En este caso vamos a
 * convertir la clase Money, para que sea representada por 2 columnas en base de
 * datos: price y curr.
 * 
 * @author Antonio Troyano
 *
 */
@Slf4j
public class MoneyType implements CompositeUserType {

	private static final String PRICE = "price";
	private static final String CURRENCY = "curr";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getPropertyNames() {
		return new String[] { PRICE, CURRENCY };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type[] getPropertyTypes() {
		return new Type[] { StandardBasicTypes.BIG_DECIMAL, StandardBasicTypes.STRING };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPropertyValue(Object component, int property) {
		final Money money = (Money) component;

		if (0 == property) {
			return money.getNumber();

		} else {
			return money.getCurrency();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPropertyValue(Object component, int property, Object value) {
		Money money = (Money) component;

		if (0 == property) {
			final BigDecimal amount = (BigDecimal) value;
			money = Money.of(amount, money.getCurrency());

		} else {
			final String currency = (String) value;
			money = Money.of(money.getNumber(), currency);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<Money> returnedClass() {
		return Money.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object x, Object y) {
		boolean isEqual = false;
		if (null == x || null == y) {
			isEqual = false;
		} else {
			isEqual = x.equals(y);
		}
		return isEqual;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode(Object x) {
		return x.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws SQLException {

		return Money.of(rs.getBigDecimal(PRICE), Monetary.getCurrency(rs.getString(CURRENCY)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws SQLException {

		Optional.ofNullable(value).ifPresentOrElse(item -> {
			try {
				Money money = (Money) item;
				st.setBigDecimal(index, BigDecimal.valueOf(money.getNumber().doubleValue()));
				st.setString(index + 1, money.getCurrency().getCurrencyCode());

			} catch (SQLException e) {
				log.error("Money conversion error: {}", e.getMessage());
			}
		}, () -> {
			try {
				st.setNull(index, StandardBasicTypes.BIG_DECIMAL.sqlType());
				st.setNull(index + 1, StandardBasicTypes.STRING.sqlType());

			} catch (SQLException e) {
				log.error("Money null conversion error: {}", e.getMessage());
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object deepCopy(Object value) {
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMutable() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Serializable disassemble(Object value, SharedSessionContractImplementor session) {
		return (Serializable) value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner) {
		return cached;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner) {

		return this.deepCopy(original);
	}

}
