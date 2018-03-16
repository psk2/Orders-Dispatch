package com.yathi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonValue;
import com.yathi.EnumValidations.BrickTypeValidator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name="orders")
@EntityListeners(AuditingEntityListener.class)
public class Orders {

    public enum BrickType {
        SAND, CLAY, CEMENT;
    }
	@Id
    @TableGenerator(name = "my_sequence"
            , table = "orders"
            , initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="orders")
    private long id;
	private long bricks;
	private boolean dispatch;


//    @BrickTypeValidator
    private BrickType brickType;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBricks() {
		return bricks;
	}
	public void setBricks(long bricks) {
		this.bricks = bricks;
	}
	public boolean isDispatch() {
		return dispatch;
	}
	public void setDispatch(boolean dispatch) {
		this.dispatch = dispatch;
	}
    public BrickType getBrickType() {
	    System.out.println("Brick type:"+brickType);
        return brickType;
    }

    @JsonValue
    public void setBrickType(BrickType brickType) {
        System.out.println("Brick type 2:"+brickType);
        this.brickType = brickType;
    }

}
