package br.com.cast.avaliacao.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.exception.CustomException;
import br.com.cast.avaliacao.model.Curso;
import br.com.cast.avaliacao.repository.CursoRepository;

/**
 * Classe que contém as validações e verificações de regras de negócio sobre o modelo Curso.
 * @author paulo
 *
 */
@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	/**
	 * Método responsável por obter uma lista de cursos
	 * @return List<Categoria>
	 */
	public List<Curso> listarCursos() {
		return cursoRepository.findAll();
	}

	/**
	 * Método responsável por obter um curso de acordo com um id especifico
	 * @param id: Long
	 * @return Curso
	 */
	public Curso buscarCursoId(Long id) {
		Optional<Curso> op = cursoRepository.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		throw new CustomException("Nenhum curso encontrado com o código informado.", HttpStatus.NOT_FOUND);
	}

	/**
	 * Método responsável por salvar um curso no banco de dados
	 * @param curso: Curso
	 * @param isUpdate: boolean
	 * @return Curso
	 */
	public Curso salvarCurso(Curso curso, boolean isUpdate) {
		verificaDataInicioFim(curso);
		
		List<Curso> listaCursos = cursoRepository.findAll();
		
		if(!listaCursos.isEmpty()) {
			if(isUpdate) {
				listaCursos.remove(curso);
			}
			validaIntervaloCurso(listaCursos, curso);
		}
		
		return cursoRepository.save(curso);
	}
	
	/**
	 * Método responsável por verificar se o novo curso possui data de inicio ou fim conflitante
	 * com outro curso ja salvo
	 * @param cursos: List<Curso>
	 * @param novoCurso: Curso
	 */
	private void validaIntervaloCurso(List<Curso> cursos, Curso novoCurso) {
		cursos.forEach(item -> {
			if(isIntervaloCurso(item.getDataInicio(), item.getDataFim(), zerarHoras(novoCurso.getDataInicio()))
					|| isIntervaloCurso(item.getDataInicio(), item.getDataFim(), zerarHoras(novoCurso.getDataFim()))) {
				throw new CustomException("Existe(m) curso(s) planejado(s) dentro do período informado.", HttpStatus.BAD_REQUEST);
			}
			});
	}
	
	/**
	 * Método que realia validações de data inicio e fim, comparando com a data atual
	 * @param curso: Curso
	 */
	private void verificaDataInicioFim(Curso curso) {
		if(curso.getDataFim().before(curso.getDataInicio())) {
			throw new CustomException("A data de inicio não pode ser superior a data final do curso.", HttpStatus.BAD_REQUEST);
		}
		
		Date dataAtual = converteDate(LocalDate.now());
		if(curso.getDataInicio().before(dataAtual)) {
			throw new CustomException("A data de inicio não pode ser menor que a data atual.", HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Método responsável por converter um localDate em Date
	 * @param localDate
	 * @return
	 */
	private static Date converteDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Método responsável por deletar um curso do banco de dados através de seu id
	 * @param id: Long
	 */
	public void deletarCurso(Long id) {
		buscarCursoId(id);
		cursoRepository.deleteById(id);
	}
	
	/**
	 * Método responsável por converter um Date em uma String
	 * @param dataHora: Date
	 * @return String
	 */
	public static String formatarDataString(Date dataHora) {
		DateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formater.format(dataHora);
	}
	
	/**
	 * Método que verifica se uma data especifica esta entre um intervalo de datas
	 * @param dataInicio: Date
	 * @param dataFim: Date
	 * @param dataVerificacao: Date
	 * @return boolean
	 */
	public boolean isIntervaloCurso(Date dataInicio, Date dataFim, Date dataVerificacao) {
		return !(dataVerificacao.before(dataInicio) || dataVerificacao.after(dataFim));
	}
	
	/**
	 * Método responsável por zerar as horas e uma respectiva data
	 * @param data: Date
	 * @return Date
	 */
	private static Date zerarHoras(Date data){
		Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
