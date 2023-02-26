package bankproject.onlinebanking.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bankproject.onlinebanking.Model.BankAccount;
import bankproject.onlinebanking.Model.Transactions;
import bankproject.onlinebanking.Service.TransactionService;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    @Autowired
    public TransactionService transactionService;

    @GetMapping("/transaction")
    public ResponseEntity<List<Transactions>> findAll()
    {
        if(transactionService.findAll()!=null)
        {
            List<Transactions> transactions= transactionService.findAll();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sender/{fromAccount}")
    public ResponseEntity<List<Transactions>> findByFromAccount(@PathVariable long fromAccount)
    {
        if(transactionService.getDetailsByAccount(fromAccount)!=null)
        {
            List<Transactions> transactions= transactionService.getDetailsByAccount(fromAccount);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/receiver/{toAccount}")
    public ResponseEntity<List<Transactions>> findByToAccount(@PathVariable long toAccount)
    {
        if(transactionService.getTransactionsByReceiver(toAccount)!=null)
        {
            List<Transactions> transactions= transactionService.getTransactionsByReceiver(toAccount);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bankaccount")
    public ResponseEntity<List<Transactions>> findByFromAccount(@RequestBody BankAccount bankAccount)
    {
        if(transactionService.getAllByAccount(bankAccount.getAccountno())!=null)
        {
            List<Transactions> transactions= transactionService.getAllByAccount(bankAccount.getAccountno());
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/transactionId/{toAccount}")
    public ResponseEntity<Transactions> findByToAccount(@PathVariable int transactionId)
    {
        if(transactionService.getTransactionsById(transactionId)!=null)
        {
            Transactions transactions= transactionService.getTransactionsById(transactionId);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}