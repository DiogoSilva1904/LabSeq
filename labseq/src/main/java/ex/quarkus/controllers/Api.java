package ex.quarkus.controllers;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import ex.quarkus.models.Result;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/labseq")
@ApplicationScoped
public class Api{

    //em vez de dar sort para descobrir a maior key, dar track é menos custoso
    private int highestKey = 3;

    //mapa para guardar os dados 
    private final Map<Integer,BigInteger> cacheMap = new HashMap();

    public Api(){
        cacheMap.put(0,BigInteger.ZERO);
        cacheMap.put(1,BigInteger.ONE);
        cacheMap.put(2,BigInteger.ZERO);
        cacheMap.put(3,BigInteger.ONE);
    }


    private BigInteger calcultaLabSeqResult(int n){
        if(n<0){
            throw new IllegalArgumentException("Negative index not allowed: " + n);
        }
        
        else if(cacheMap.containsKey(n)){
            return cacheMap.get(n);
        }

        if (n<1000){
            BigInteger result = calcultaLabSeqResult(n-4).add(calcultaLabSeqResult(n-3));
            cacheMap.put(n,result);
            highestKey = Math.max(n,highestKey);
            return result;
        }
        else{
            return calculateHighValues(n);
        }
    }

    private BigInteger calculateHighValues(int n){
        for(int i = highestKey+1;i<=n;i++){
            BigInteger result = calcultaLabSeqResult(i-4).add(calcultaLabSeqResult(i-3));
            cacheMap.put(i,result);
        }

        highestKey = Math.max(highestKey,n);

        return cacheMap.get(n);

    }

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLabSeq(@PathParam("n") int n){
        if(n<0){
            throw new IllegalArgumentException("Negative index not allowed: " + n);
        }
        long start_time = System.currentTimeMillis();
        BigInteger result = calcultaLabSeqResult(n);
        long end_time = System.currentTimeMillis();

        long duration = end_time - start_time;

        Result finalResult = new Result(result.toString(),duration);

        return Response.ok(finalResult).build();
    }

}