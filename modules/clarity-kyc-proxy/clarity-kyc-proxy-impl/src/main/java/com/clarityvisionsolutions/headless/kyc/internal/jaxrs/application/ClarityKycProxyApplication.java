package com.clarityvisionsolutions.headless.kyc.internal.jaxrs.application;

import javax.annotation.Generated;

import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author 17XZ0Z2
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false",
		"osgi.jaxrs.application.base=/clarity-kyc-proxy",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=ClarityKycProxy"
	},
	service = Application.class
)
@Generated("")
public class ClarityKycProxyApplication extends Application {
}