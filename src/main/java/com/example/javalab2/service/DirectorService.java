package com.example.javalab2.service;

import com.example.javalab2.dto.DirectorDto;
import com.example.javalab2.entity.Director;
import com.example.javalab2.exception.EntityNotFoundException;
import com.example.javalab2.mapper.DirectorMapper;
import com.example.javalab2.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"main"})
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorDto saveDirector(DirectorDto directorDto) {
        Director director = directorRepository.save(directorMapper.toEntity(directorDto));
        return directorMapper.toDto(director);
    }

    public List<DirectorDto> findAllDirectors() {
        return directorMapper.toDto(directorRepository.findAll());
    }

    public void deleteAllDirectors() {
        directorRepository.deleteAll();
    }

    @Cacheable
    public List<DirectorDto> findDirectorsByName(String name) {
        return directorMapper.toDto(directorRepository.findDirectorsByName(name));
    }

    public List<DirectorDto> findDirectorsBySurname(String surname) {
        return directorMapper.toDto(directorRepository.findDirectorsBySurname(surname));
    }

    public List<DirectorDto> findDirectorsByPatronymic(String patronymic) {
        return directorMapper.toDto(directorRepository.findDirectorsByPatronymic(patronymic));
    }

    public List<DirectorDto> findDirectorsByBirthdate(LocalDate birthdate) {
        return directorMapper.toDto(directorRepository.findDirectorsByBirthdate(birthdate));
    }

    public List<DirectorDto> findDirectorsByOscar(Boolean oscar) {
        return directorMapper.toDto(directorRepository.findDirectorsByOscar(oscar));
    }

    public void deleteDirectorById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id <= 0");
        }
        directorRepository.deleteById(id);
    }

    public DirectorDto findDirectorById(Long id) throws EntityNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("id <= 0");
        }
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (optionalDirector.isPresent()) {
            return directorMapper.toDto(optionalDirector.get());
        } else {
            throw new EntityNotFoundException(String.format("Director with id %d not found", id));
        }
    }

    public void deleteDirectorByNameAndSurnameAndPatronymic(String name, String surname, String patronymic) {
        directorRepository.deleteDirectorByNameAndSurnameAndPatronymic(name, surname, patronymic);
    }
}
