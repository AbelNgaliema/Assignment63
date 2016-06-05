package Repository;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Domain.Branch;
import Factories.BranchFactory;
import Repository.BranchRepository.BranchRepository;
import Repository.BranchRepository.Implementation.BranchRepositoryImpl;

/**
 * Created by AbelN on 07/05/2016.
 */
public class TestBranchRepo extends AndroidTestCase {
    private static final String TAG="BRANCH TEST";
    private Long id;

//
    @Test

    public void testCreateReadUpdateDelete() throws Exception {
        BranchRepository repo = new BranchRepositoryImpl(this.getContext());
        Map<String,String> values = new HashMap<String,String>();


        values.put("manager","Abel");
        values.put("address", "Victoria Road");


        //Object

        Branch branch = BranchFactory.createBranch(values,021363252);
        // CREATE

        Branch insertedEntity = repo.save(branch);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Branch> books = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",books.size()>0);

        //READ ENTITY
        Branch entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Branch updateEntity = new Branch.Builder()
                .id(entity.getId())
                .copy(entity)
                .manager("KRISTOS")
                .build();
        repo.update(updateEntity);
        Branch newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","KRISTOS",newEntity.getManager());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Branch deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }






}

