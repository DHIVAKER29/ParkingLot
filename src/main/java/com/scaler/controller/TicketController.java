package com.scaler.controller;

import com.scaler.dtos.IssueTicketRepsonseDto;
import com.scaler.dtos.IssueTicketRequestDto;
import com.scaler.service.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketRepsonseDto issueTicket(IssueTicketRequestDto issueTicketRequestDto) {
        return null;
    }
}
