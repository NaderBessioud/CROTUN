package tn.esprit.CROTUN.Services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Entities.Slice;
import tn.esprit.CROTUN.Repositories.LoanRepository;
import tn.esprit.CROTUN.Repositories.SliceRepository;

@Service
public class SliceServiceImpl implements ISliceService {
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	SliceRepository sliceRepository;


	@Override
	public Slice calculMensualite(Long id) {
		Loan l=loanRepository.findById(id).get();
		Slice s=sliceRepository.findById(id).get();
		
		double mens=0;
		
		mens=(l.getTotalAmount()*(l.getInterest()/12))/
				(1-Math.pow((1+l.getInterest()/12),-l.getLoanPeriodInMonths()));
		
		s.setPrice(mens);
		
		return s;
	}


	@Override
	public Slice addSlice(Slice s, Long IdL) {
		s.setLoanSlice(loanRepository.findById(IdL).orElse(null));
		
		Loan l=loanRepository.findById(IdL).get();
		
		
		double mens=0;
		
		mens=(l.getTotalAmount()*(l.getInterest()/12))/
				(1-Math.pow((1+l.getInterest()/12),-l.getLoanPeriodInMonths()));
		
		s.setPrice(mens);
		sliceRepository.save(s);
		
		return s;
	}
	
	@Override
	public Slice retrieveSlice(long id) {
		return sliceRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Slice> retrieveAllSlices() {
		List<Slice> Slices = (List<Slice>) sliceRepository.findAll();
		return Slices;
	}


	@Override
	public Slice addSliceLists(Slice s, Long IdL) {
		s.setLoanSlice(loanRepository.findById(IdL).orElse(null));
		
		Loan l=loanRepository.findById(IdL).get();
		
		
		double mens=0;
		
		
		for (int i=0;i<l.getLoanPeriodInMonths();i++) {
			
			Slice sl  = new Slice ();
			sl.setLoanSlice(loanRepository.findById(IdL).orElse(null));
			mens=(l.getTotalAmount()*(l.getInterest()/12))/
					(1-Math.pow((1+l.getInterest()/12),-l.getLoanPeriodInMonths()));
			
			sl.setPrice(mens);
			sl.setRepaymentDate(l.getDateStartLoan().plusMonths(i));
			sl.setVerified(false);
			sliceRepository.save(sl);
			
		}
		return s;
	}
	
	
    public List<Slice> listAll() {
        return (List<Slice>) sliceRepository.findAll();
    }
	
    @Override
  	public List<Slice> retrieveAllSlicePaye() {
  		List<Slice> list=(List<Slice>) sliceRepository.findAll();
  	 Stream<Slice> lo_s = list.stream().filter(
  				 Slice -> Slice.getVerified().equals(true)
  				);
  	List<Slice> lo_list = lo_s.collect(Collectors.toList());
  		
  		return lo_list ;
  	}
  	@Override
  	public List<Slice> retrieveAllSliceNonPaye() {
  		List<Slice> list=(List<Slice>) sliceRepository.findAll();
  		 Stream<Slice> lo_s = list.stream().filter(
  					 Slice -> Slice.getVerified().equals(false)
  					);
  		List<Slice> lo_list = lo_s.collect(Collectors.toList());
  			
  			return lo_list ;
  	}
	
}
