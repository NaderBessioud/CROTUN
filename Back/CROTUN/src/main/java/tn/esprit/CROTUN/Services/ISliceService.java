package tn.esprit.CROTUN.Services;

import java.util.List;

import tn.esprit.CROTUN.Entities.Loan;
import tn.esprit.CROTUN.Entities.Slice;

public interface ISliceService {
	
	public Slice calculMensualite(Long id);
	
	public Slice addSlice(Slice s,Long IdL);
	
	public Slice retrieveSlice(long id);
	
	public List<Slice> retrieveAllSlices();
	
	public Slice addSliceLists(Slice s,Long IdL);
	
	public List<Slice> listAll();
	
	List<Slice> retrieveAllSlicePaye();
	List<Slice> retrieveAllSliceNonPaye();
	
}
