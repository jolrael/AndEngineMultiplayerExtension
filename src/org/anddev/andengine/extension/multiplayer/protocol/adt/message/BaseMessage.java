package org.anddev.andengine.extension.multiplayer.protocol.adt.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Nicolas Gramlich
 * @since 15:27:13 - 18.09.2009
 */
public abstract class BaseMessage implements IMessage {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public abstract short getFlag();

	protected abstract void onReadTransmissionData(final DataInputStream pDataInputStream) throws IOException;
	protected abstract void onWriteTransmissionData(final DataOutputStream pDataOutputStream) throws IOException;

	/**
	 * Append all data of this {@link BaseMessage} to the {@link StringBuilder}.
	 * @param pStringBuilder
	 */
	protected abstract void onAppendTransmissionDataForToString(final StringBuilder pStringBuilder);

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		sb.append(this.getClass().getSimpleName())
		.append("[getFlag()=").append(this.getFlag());

		this.onAppendTransmissionDataForToString(sb);

		sb.append("]");

		return sb.toString();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}

		final BaseMessage other = (BaseMessage) obj;

		return this.getFlag() == other.getFlag();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	public void transmit(final DataOutputStream pDataOutputStream) throws IOException {
		pDataOutputStream.writeShort(this.getFlag());
		this.onWriteTransmissionData(pDataOutputStream);
	}
	
	@Override
	public void read(final DataInputStream pDataInputStream) throws IOException {
		this.onReadTransmissionData(pDataInputStream);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
