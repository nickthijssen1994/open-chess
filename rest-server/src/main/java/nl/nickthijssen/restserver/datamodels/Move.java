package nl.nickthijssen.restserver.datamodels;

import javax.persistence.*;

@Entity
public class Move {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
}
