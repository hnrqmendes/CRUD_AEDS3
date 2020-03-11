import java.io.File;
import java.io.*;

public class CRUD {

    public final String diretorio = "dados";

    public RandomAccessFile arquivo;
    public HashExtensivel índiceDireto;
    public ArvoreBMais_String_Int índiceIndireto;
  
    public CRUD(String nomeArquivo) throws Exception {

        File d = new File(this.diretorio);
        if( !d.exists() )
            d.mkdir();

        arquivo = new RandomAccessFile(this.diretorio+"/"+nomeArquivo+".db", "rw");
        if(arquivo.length()<4)
            arquivo.writeInt(0);  // cabeçalho do arquivo

        índiceDireto = new HashExtensivel( 10, 
                           this.diretorio+"/diretorio."+nomeArquivo+".idx", 
                           this.diretorio+"/cestos."+nomeArquivo+".idx");

        índiceIndireto = new ArvoreBMais_String_Int( 10,
                           this.diretorio+"/arvoreB."+nomeArquivo+".idx");
    }

    /**
     * 
     * @param nome
     * @param email
     * @param senha
     * @return id do usuario criado no db
     * @throws Exception
     */
    public int create(String nome, String email, String senha) throws Exception {

        arquivo.seek(0);
        int id = arquivo.readInt() + 1;// ler o ultimo id usado e somar um para obter o novo id
        arquivo.seek(0);
        arquivo.writeInt(id);// atualiza o id

        Usuario temp = new Usuario(id, nome, email, senha);

        arquivo.seek(arquivo.length());// aponta o ponteiro para o fim do arquivo

        arquivo.writeChar(' ');// escreve a lapide
        arquivo.writeInt(temp.toByteArray().length); // escreve o tamanho do registro
        arquivo.write(temp.toByteArray());// escreve o registro
        
        
        // idexar
        return id;
    }
    public boolean update(int id){
        boolean failStatus = false;

        return failStatus;
    }
    public boolean delete(int id){
        boolean failStatus = false;

        return failStatus;
    }
}