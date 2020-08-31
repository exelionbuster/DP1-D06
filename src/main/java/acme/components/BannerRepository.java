
package acme.components;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("select b from Banner b where b.creditCard.id <> null")
	List<Banner> findManyBanners(PageRequest pr);

	@Query("select count(b) from Banner b where b.creditCard.id <> null")
	int countBanners();

	default Banner findRandomBanner() {
		Banner result;
		int bannerCount, bannerIndex;
		ThreadLocalRandom random;
		PageRequest page;
		List<Banner> banners;

		bannerCount = this.countBanners();
		if (bannerCount != 0) {
			random = ThreadLocalRandom.current();
			bannerIndex = random.nextInt(0, bannerCount);

			page = PageRequest.of(bannerIndex, 1);
			banners = this.findManyBanners(page);
			result = banners.isEmpty() ? null : banners.get(0);
		} else {
			result = null;
		}

		return result;
	}
}
