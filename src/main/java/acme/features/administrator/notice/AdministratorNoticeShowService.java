
package acme.features.administrator.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.notices.Notice;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorNoticeShowService implements AbstractShowService<Administrator, Notice> {

	@Autowired
	AdministratorNoticeRepository respository;


	@Override
	public boolean authorise(final Request<Notice> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Notice> request, final Notice entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "header", "title", "creationDate", "deadline", "body", "links");

	}

	@Override
	public Notice findOne(final Request<Notice> request) {
		assert request != null;

		Notice res;
		int id;

		id = request.getModel().getInteger("id");
		res = this.respository.findOneById(id);
		return res;
	}

}
