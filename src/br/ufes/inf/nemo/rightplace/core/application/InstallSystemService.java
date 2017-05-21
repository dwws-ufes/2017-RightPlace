package br.ufes.inf.nemo.rightplace.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.rightplace.core.domain.User;
import br.ufes.inf.nemo.rightplace.core.domain.RightplaceConfiguration;
import br.ufes.inf.nemo.rightplace.core.exceptions.SystemInstallFailedException;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Local
public interface InstallSystemService extends Serializable {
	/**
	 * TODO: document this method.
	 * 
	 * @param config
	 * @param admin
	 * @throws SystemInstallFailedException
	 */
	void installSystem(RightplaceConfiguration config, User admin) throws SystemInstallFailedException;
}
