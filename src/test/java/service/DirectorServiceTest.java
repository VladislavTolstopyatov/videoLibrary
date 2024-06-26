package service;

import com.example.javalab2.dto.DirectorDto;
import com.example.javalab2.entity.Director;
import com.example.javalab2.exception.EntityNotFoundException;
import com.example.javalab2.mapper.DirectorMapper;
import com.example.javalab2.repository.DirectorRepository;
import com.example.javalab2.service.DirectorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {
    @Mock
    private DirectorRepository directorRepository;
    @Mock
    private DirectorMapper directorMapper;
    @InjectMocks
    private DirectorService directorService;

    @Test
    public void saveDirector() {
        final Director director = getDirector();
        final DirectorDto directorDto = getDirectorDto();

        when(directorMapper.toEntity(directorDto)).thenReturn(director);
        when(directorRepository.save(director)).thenReturn(director);
        when(directorMapper.toDto(director)).thenReturn(directorDto);

        assertThat(directorDto).isEqualTo(directorService.saveDirector(directorDto));
    }

    @Test
    public void findAllDirectorsWhenDirectorsExists() {
        final List<Director> directorList = List.of(getDirector());
        final List<DirectorDto> directorDtos = List.of(getDirectorDto());

        when(directorRepository.findAll()).thenReturn(directorList);
        when(directorMapper.toDto(directorList)).thenReturn(directorDtos);
        assertThat(directorDtos.get(0)).isEqualTo(directorService.findAllDirectors().get(0));
    }

    @Test
    public void findAllDirectorsWhenDirectorsNotExists() {
        when(directorRepository.findAll()).thenReturn(null);
        assertThat(directorService.findAllDirectors()).isEmpty();
    }

    @Test
    void deleteAllDirectors() {
        directorService.deleteAllDirectors();
        verify(directorRepository, times(1)).deleteAll();
    }

    @Test
    public void findAllDirectorsByNameWhenDirectorsExists() {
        final List<Director> directorList = List.of(getDirector());
        final List<DirectorDto> directorDtos = List.of(getDirectorDto());
        final String name = directorList.get(0).getName();

        when(directorRepository.findDirectorsByName(name)).thenReturn(directorList);
        when(directorMapper.toDto(directorList)).thenReturn(directorDtos);
        assertThat(directorDtos.get(0)).isEqualTo(directorService.findDirectorsByName(name).get(0));
    }

    @Test
    public void findAllDirectorsByNameWhenDirectorsNotExists() {
        final String name = getDirectorDto().getName();
        when(directorRepository.findDirectorsByName(name)).thenReturn(null);
        assertThat(directorService.findDirectorsByName(name)).isEmpty();
    }

    @Test
    public void findAllDirectorsBySurnameWhenDirectorsExists() {
        final List<Director> directorList = List.of(getDirector());
        final List<DirectorDto> directorDtos = List.of(getDirectorDto());
        final String surname = directorList.get(0).getSurname();

        when(directorRepository.findDirectorsBySurname(surname)).thenReturn(directorList);
        when(directorMapper.toDto(directorList)).thenReturn(directorDtos);
        assertThat(directorDtos.get(0)).isEqualTo(directorService.findDirectorsBySurname(surname).get(0));
    }

    @Test
    public void findAllDirectorsBySurnameWhenDirectorsNotExists() {
        final String surname = getDirectorDto().getSurname();
        when(directorRepository.findDirectorsBySurname(surname)).thenReturn(null);
        assertThat(directorService.findDirectorsBySurname(surname)).isEmpty();
    }

    @Test
    public void findAllDirectorsByPatronymicWhenDirectorsExists() {
        final List<Director> directorList = List.of(getDirector());
        final List<DirectorDto> directorDtos = List.of(getDirectorDto());
        final String patronymic = directorList.get(0).getPatronymic();

        when(directorRepository.findDirectorsByPatronymic(patronymic)).thenReturn(directorList);
        when(directorMapper.toDto(directorList)).thenReturn(directorDtos);
        assertThat(directorDtos.get(0)).isEqualTo(directorService.findDirectorsByPatronymic(patronymic).get(0));
    }

    @Test
    public void findAllDirectorsByPatronymicWhenDirectorsNotExists() {
        final String patronymic = getDirectorDto().getPatronymic();
        when(directorRepository.findDirectorsByPatronymic(patronymic)).thenReturn(null);
        assertThat(directorService.findDirectorsByPatronymic(patronymic)).isEmpty();
    }

    @Test
    public void findAllDirectorsByBirthdateWhenDirectorsExists() {
        final List<Director> directorList = List.of(getDirector());
        final List<DirectorDto> directorDtos = List.of(getDirectorDto());
        final LocalDate birthdate = getDirectorDto().getBirthdate();

        when(directorRepository.findDirectorsByBirthdate(birthdate)).thenReturn(directorList);
        when(directorMapper.toDto(directorList)).thenReturn(directorDtos);
        assertThat(directorDtos.get(0)).isEqualTo(directorService.findDirectorsByBirthdate(birthdate).get(0));
    }

    @Test
    public void findAllDirectorsByBirthdateWhenDirectorsNotExists() {
        final LocalDate birthdate = getDirectorDto().getBirthdate();
        when(directorRepository.findDirectorsByBirthdate(birthdate)).thenReturn(null);
        assertThat(directorService.findDirectorsByBirthdate(birthdate)).isEmpty();
    }

    @Test
    public void findAllDirectorsByOscarWhenDirectorsExists() {
        final List<Director> directorList = List.of(getDirector());
        final List<DirectorDto> directorDtos = List.of(getDirectorDto());
        final Boolean oscar = directorDtos.get(0).getOscar();

        when(directorRepository.findDirectorsByOscar(oscar)).thenReturn(directorList);
        when(directorMapper.toDto(directorList)).thenReturn(directorDtos);
        assertThat(directorDtos.get(0)).isEqualTo(directorService.findDirectorsByOscar(oscar).get(0));
    }

    @Test
    public void findAllDirectorsByOscarWhenDirectorsNotExists() {
        final Boolean oscar = getDirectorDto().getOscar();
        when(directorRepository.findDirectorsByOscar(oscar)).thenReturn(null);
        assertThat(directorService.findDirectorsByOscar(oscar)).isEmpty();
    }

    @Test
    void deleteDirectorByInvalidId() {
        final Long invalidId = -1L;
        assertThrows(IllegalArgumentException.class, () -> directorService.deleteDirectorById(invalidId));
    }

    @Test
    void deleteDirectorByValidId() {
        final Long id = 1L;
        directorService.deleteDirectorById(id);
        verify(directorRepository, times(1)).deleteById(id);
    }

    @Test
    void findDirectorByInvalidId() {
        final Long invalidId = -1L;
        assertThrows(IllegalArgumentException.class, () -> directorService.findDirectorById(invalidId));
    }

    @Test
    void findDirectorByIdWhenDirectorNotExists() {
        final Long id = getDirectorDto().getId();
        final Director director = getDirector();
        when(directorRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> directorService.findDirectorById(id));
    }

    @Test
    void findDirectorByIdWhenDirectorExists() throws EntityNotFoundException {
        final Director director = getDirector();
        final DirectorDto directorDto = getDirectorDto();
        final Long id = directorDto.getId();
        when(directorRepository.findById(id)).thenReturn(Optional.of(director));
        when(directorMapper.toDto(director)).thenReturn(directorDto);
        assertThat(directorDto).isEqualTo(directorService.findDirectorById(id));
    }

    @Test
    void deleteDirectorByNameAndSurnameAndPatronymic() {
        final String name = getDirectorDto().getName();
        final String surname = getDirectorDto().getSurname();
        final String patronymic = getDirectorDto().getPatronymic();
        directorService.deleteDirectorByNameAndSurnameAndPatronymic(name, surname, patronymic);
        verify(directorRepository, times(1)).deleteDirectorByNameAndSurnameAndPatronymic(name, surname, patronymic);
    }

    private static DirectorDto getDirectorDto() {
        return new DirectorDto(1L,
                "name",
                "surname",
                "patronymic",
                LocalDate.of(2003, 3, 3),
                Boolean.TRUE,
                Collections.emptyList());
    }

    private static Director getDirector() {
        return new Director(1L,
                "name",
                "surname",
                "patronymic",
                LocalDate.of(2003, 3, 3),
                Boolean.TRUE,
                Collections.emptyList());
    }
}
